module Main where

import Pruebas

main :: IO ()
main = do
    putStrLn "Running tests..."
    putStrLn $ "Test Distance A to B: " ++ testDistanceAtoB
    putStrLn $ "Test Distance B to C: " ++ testDistanceBtoC
    putStrLn $ "Test City Name A: " ++ testCityNameA
    putStrLn $ "Test City Distance A to B: " ++ testCityDistanceAtoB
    putStrLn $ "Test Quality High Capacity: " ++ testQualityHighCapacity
    putStrLn $ "Test Quality Low Delay: " ++ testQualityLowDelay
    putStrLn $ "Test Link AB Connects A: " ++ testLinkABConnectsA
    putStrLn $ "Test Link AB Does Not Connect C: " ++ testLinkABDoesNotConnectC
    putStrLn $ "Test Link AB Links A to B: " ++ testLinkABLinksAtoB
    putStrLn $ "Test Link AB Delay: " ++ testLinkABDelay
    putStrLn $ "Test Tunnel AB Connects A to B: " ++ testTunnelABConnectsAtoB
    putStrLn $ "Test Tunnel AB Does Not Connect A to C: " ++ testTunnelABDoesNotConnectAtoC
    putStrLn $ "Test Tunnel AB Uses Link AB High: " ++ testTunnelABUsesLinkABHigh
    putStrLn $ "Test Tunnel AB Delay: " ++ testTunnelABDelay
