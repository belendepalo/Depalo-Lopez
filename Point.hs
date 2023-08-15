module Point (Point, newP, difP)
   where

import Math

data Point = Poi Int Int deriving (Eq, Show)


newP :: Int -> Int -> Point
newP = Poi

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x1 y1) (Poi x2 y2) = sqrt(fromIntegral (sqr(x2 - x1) + sqr(y2 - y1)))
