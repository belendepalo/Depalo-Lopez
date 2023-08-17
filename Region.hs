module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import City
import Quality
import Link
import Tunel
import Tunel (connectsT)
import Link (linksL)

data Region = Reg [City] [Link] [Tunel]

newR :: Region
newR = Reg 

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities _ _) newCity | newCity `elem` cities = error "The City is already in the Region" 
                                | otherwise = Reg (newCity : cities) _ _ 

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ links _) c0 c1 = connectsT c0 c1 links

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
