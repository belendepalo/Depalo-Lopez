module Point (Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Poi

difP :: Point -> Point -> Float  -- distancia absoluta
difP p p1 = sqrt((p)**2 + (p1)**2)