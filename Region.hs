{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Redundant bracket" #-}

module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel]

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg cities links tunnels) newCity
   | newCity `elem` cities = error "The City is already in the Region"
   | any (`sameCoordinates` newCity) cities = error "A city with the same coordinates already exists"
   | otherwise = Reg (newCity:cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunnels) c0 c1 quality
   | c0 == c1 = error "Cannot link a city to itself"
   | not (citiesInRegion c0 c1 cities)  = error "One or both cities are not in the region"
   | sameCoordinates c0 c1 = error "Both cities cannot have the same coordinates"
   | otherwise = Reg cities (newL c0 c1 quality:links) tunnels

tunelR :: Region -> [City] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region@(Reg cities links tunnels) citiesToConnect
   | length citiesToConnect < 2 = error "At least two cities are required to create a tunnel"
   | not (allCitiesConnected region citiesToConnect) = error "Not all cities are connected"
   | otherwise = Reg cities links (newT allLinks:tunnels)
   where 
      allLinks = getLinksBetweenCities citiesToConnect links

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunnels) c0 c1 = any (connectsT c0 c1) tunnels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) c0 c1 = any (\link -> linksL c0 c1 link || linksL c1 c0 link) links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cities links tunnels) c0 c1 
   | not (citiesInRegion c0 c1 cities) = error "One or both cities are not in the region"
   | null connectingLinks = error "No direct links between the cities"
   | otherwise = sum (map delayL connectingLinks)
   where
      connectingLinks = [link | link <- links, linksL c0 c1 link || linksL c1 c0 link]

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region@(Reg _ links tunnels) c0 c1
   | null linksConnectingCities = error "There are connection errors between the cities"
   | otherwise = capacityLink - capacityUsed region (head linksConnectingCities)
   where 
      linksConnectingCities = getLink c0 c1 links
      capacityLink = capacityL (head linksConnectingCities)

capacityUsed :: Region -> Link -> Int
capacityUsed (Reg _ _ tunels) link = length [tunel | tunel <- tunels, usesT link tunel]

citiesInRegion :: City -> City -> [City] -> Bool
citiesInRegion c0 c1 cities = (c0 `elem` cities) && (c1 `elem` cities)

getAllCities :: Region -> [City]
getAllCities (Reg cities _ _) = cities

getAllLinks :: Region -> [Link]
getAllLinks (Reg _ links _) = links

getAllTunnels :: Region -> [Tunel]
getAllTunnels (Reg _ _ tunnels) = tunnels

allCitiesConnected :: Region -> [City] -> Bool
allCitiesConnected region citiesToConnect =
    all (\city -> any (\neighbor -> linkedR region city neighbor || linkedR region neighbor city) (filter (/= city) citiesToConnect)) citiesToConnect

getLinksBetweenCities :: [City] -> [Link] -> [Link]
getLinksBetweenCities citiesToConnect links =
    [link | link <- links, linksConnectCities link]
    where
        linksConnectCities (Lin c0 c1 _) = c0 `elem` citiesToConnect && c1 `elem` citiesToConnect

getLink :: City -> City -> [Link] -> [Link]
getLink c0 c1 links = [link | link <- links, connectsL c0 link || connectsL c1 link]


-- Opciones para el dealyR

delayR3 :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR3 (Reg cities links _) c0 c1
   | not (citiesInRegion c0 c1 cities) = error "One or both cities are not in the region"
   | null connectingLinks = error "No direct links between the cities"
   | otherwise = sum (map delayL connectingLinks)
   where
      connectingLinks = [link | link <- links, linksL c0 c1 link || linksL c1 c0 link]

delayR0 :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR0 (Reg cities links _) c0 c1
   | not (citiesInRegion c0 c1 cities) = error "One or both cities are not in the region"
   | null connectingLinks = error "No direct links between the cities"
   | otherwise = sum (map delayL connectingLinks) / fromIntegral (length connectingLinks)
   where
      connectingLinks = [link | link <- links, linksL c0 c1 link || linksL c1 c0 link]

delayR1 :: Region -> City -> City -> Float
delayR1 (Reg cities _ tunnels) c0 c1
   | not (citiesInRegion c0 c1 cities) = error "One or both cities are not in the region"
   | isConnected = delayT connectedTunnel
   | otherwise  = error "The cities are not connected by a tunnel"
   where
      isConnected = any (connectsT c0 c1) tunnels
      connectedTunnel = head [tunnel | tunnel <- tunnels, connectsT c0 c1 tunnel]

delayR2 :: Region -> City -> City -> Float
delayR2 (Reg cities links tunnels) c0 c1
   | not (citiesInRegion c0 c1 cities) = error "One or both cities are not in the region"
   | isConnected = delayT connectedTunnel
   | isLinked = averageDelayL connectingLinks
   | otherwise = error "The cities are not connected or linked"
   where
      isConnected = any (connectsT c0 c1) tunnels
      connectedTunnel = head [tunnel | tunnel <- tunnels, connectsT c0 c1 tunnel]
      isLinked = any (\link -> linksL c0 c1 link || linksL c1 c0 link) links
      connectingLinks = [link | link <- links, linksL c0 c1 link || linksL c1 c0 link]
      averageDelayL selectedLinks = sum (map delayL selectedLinks) / fromIntegral (length selectedLinks)
