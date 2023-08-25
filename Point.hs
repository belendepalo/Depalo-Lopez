module Point (Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Poi

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x0 y0) (Poi x1 y1) = sqrt(fromIntegral ((x1 - x0)^2 + (y1 - y0)^2))

