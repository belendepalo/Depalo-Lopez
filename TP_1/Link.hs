module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City 
import Quality 
import Point 

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL c0 c1 quality
   | distanceC c0 c1 == 0 = error "Cannnot create a link with same coordinates"
   | otherwise = Lin c0 c1 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin c0 c1 _ ) = (city == c0) || (city == c1)

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 (Lin c0 c1 _)
   | city1 == city2 = error "Cities cannot be the same"
   | otherwise = (city1 == c0 && city2 == c1) || (city1 == c1 && city2 == c0)

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin c0 c1 quality) = delayQ quality * distanceC c0 c1