module City ( City, newC, nameC, distanceC )
   where

import Point

data City = Cit String Point deriving (Eq, Show)



newC :: String -> Point -> City
newC = Cit

nameC :: City -> String
nameC Cit s _ = s

distanceC :: City -> City -> Float
distanceC Cit _ _ = difP p0 p1