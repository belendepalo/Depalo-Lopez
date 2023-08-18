module Main where

{-
el comando para compilar es: ghc -odir build/o_files -hidir build/hi_files -o pruebas main.hs
para ejecutarlo es: ./pruebas
y para las carpetas donde se guardan los archivos basura son los tres siguentes:
mkdir build
mkdir build/o_files
mkdir build/hi_files
-} 

import Pruebas

main :: IO ()
main = do
    putStrLn "Running tests..."
    putStrLn testDistanceAtoB
    putStrLn testDistanceBtoC
    putStrLn testCityNameA
    putStrLn testCityDistanceAtoB
    putStrLn testQualityHighCapacity
    putStrLn testQualityLowDelay
    putStrLn testLinkABConnectsA
    putStrLn testLinkABDoesNotConnectC
    putStrLn testLinkABLinksAtoB
    putStrLn testLinkABDelay
    putStrLn testTunnelABConnectsAtoB
    putStrLn testTunnelABDoesNotConnectAtoC
    putStrLn testTunnelABUsesLinkABHigh
    putStrLn testTunnelABDelay
