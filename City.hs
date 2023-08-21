module City ( City, newC, nameC, distanceC )
   where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC = Cit

nameC :: City -> String
nameC (Cit city_name _) = city_name

distanceC :: City -> City -> Float
distanceC (Cit _ p0) (Cit _ p1) = difP p0 p1

sameCoordinates :: City -> City -> Bool
sameCoordinates (Cit _ poi0) (Cit _ poi1) = poi0 == poi1