{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Redundant bracket" #-}

module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg cities links tunnels) newCity
   | newCity `elem` cities = error "The City is already in the Region"
   | any (\city -> distanceC newCity city == 0) cities  = error "A city with the same coordinates already exists"
   | otherwise = Reg (newCity:cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunnels) c0 c1 quality
   | c0 == c1 = error "Cannot link a city to itself"
   | not (citiesInRegion c0 c1 cities)  = error "One or both cities are not in the region"
   | distanceC c0 c1 == 0 = error "Both cities cannot have the same coordinates"
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
   | null connectingTunnels = error "No direct links between the cities"
   | otherwise = delayT (head connectingTunnels)
   where
      connectingTunnels = [tunnel | tunnel <- tunnels, connectsT c0 c1 tunnel || connectsT c1 c0 tunnel]

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

allCitiesConnected :: Region -> [City] -> Bool
allCitiesConnected region@(Reg cities _ _) citiesToConnect =
   let startCity = head citiesToConnect
       visited = dfs region [startCity] []
   in all (`elem` visited) citiesToConnect

dfs :: Region -> [City] -> [City] -> [City] -- búsqueda en profundidad, Depth-First Search, DFS en inglés
dfs _ [] visited = visited
dfs region@(Reg cities _ _) (current:stack) visited
   | current `elem` visited = dfs region stack visited
   | otherwise =
      let neighbors = filter (\city -> city /= current && linkedR region city current && notElem city visited) cities
      in dfs region (neighbors ++ stack) (current:visited)

getLinksBetweenCities :: [City] -> [Link] -> [Link]
getLinksBetweenCities citiesToConnect links = [link | link <- links, linksConnectingCities link]
   where
      linksConnectingCities link = any (`connectsL` link) citiesToConnect

getLink :: City -> City -> [Link] -> [Link]
getLink c0 c1 links = [link | link <- links, (connectsL c0 link && connectsL c1 link) || (connectsL c1 link && connectsL c0 link)]