module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import City
import Quality
import Link
import Tunel


data Region = Reg [City] [Link] [Tunel]

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities _ _) newCity
   | newCity `elem` cities = error "The City is already in the Region"
   | otherwise = Reg (newCity:cities) _ _

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunnels) c0 c1 quality
   | c0 == c1 = error "Cannot link a city to itself"
   | c0 `notElem` cities || c1 `notElem` cities = error "One or both cities are not in the region"
   | otherwise = Reg cities (newL c0 c1 quality:links) tunnels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunnels)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunnels) c0 c1 = any (connectsT c0 c1) tunnels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) c0 c1 = any (\link -> linksL c0 c1 link || linksL c1 c0 link) links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg _ links _) c0 c1
   |null connectingLinks = error "No direct links between the cities"
   |otherwise = sum (map delayL connectingLinks) / fromIntegral (length connectingLinks)
   where connectingLinks = [link | link <- links, linksL c0 c1 link || linksL c1 c0]

delayR0 :: Region -> City -> City -> Float
delayR0 (Reg _ _ tunnels) c0 c1 
    | isConnected = delayT connectedTunnel
    | otherwise  = error "The cities are not connected by a tunnel"
    where
        isConnected = any (connectsT c0 c1) tunnels
        connectedTunnel = head [tunnel | tunnel <- tunnels, connectsT c0 c1 tunnel]


availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
