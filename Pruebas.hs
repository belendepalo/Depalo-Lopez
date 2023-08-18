module Pruebas 
        where

import City
import Link 
import Point
import Quality
import Tunel

-- Instancias para Point
pointA :: Point
pointA = newP 0 0
pointB :: Point
pointB = newP 3 4
pointC :: Point
pointC = newP 5 12

-- Instancias para City
cityA :: City
cityA = newC "CityA" pointA
cityB :: City
cityB = newC "CityB" pointB
cityC :: City
cityC = newC "CityC" pointC

-- Instancias para Quality
qualityHigh :: Quality
qualityHigh = newQ "High" 5 0.5
qualityLow :: Quality
qualityLow = newQ "Low" 2 1.5

-- Instancias para Link
linkABHigh :: Link
linkABHigh = newL cityA cityB qualityHigh
linkABLow :: Link
linkABLow = newL cityA cityB qualityLow
linkBCHigh :: Link
linkBCHigh = newL cityB cityC qualityHigh

-- Instancias para Tunel
tunnelAB :: Tunel
tunnelAB = newT [linkABHigh, linkABLow]
tunnelBC :: Tunel
tunnelBC = newT [linkBCHigh]

testDistanceAtoB = 
    let expected = 5.0
        result = difP pointA pointB
    in if result == expected 
       then "Test Distance A to B: Passed"
       else "Test Distance A to B: Failed. Expected " ++ show expected ++ ", got " ++ show result

testDistanceBtoC = 
    let expected = 8.246211251235321
        result = difP pointB pointC
    in if result == expected 
       then "Test Distance B to C: Passed"
       else "Test Distance B to C: Failed. Expected " ++ show expected ++ ", got " ++ show result

testCityNameA = 
    let expected = "CityA"
        result = nameC cityA
    in if result == expected 
       then "Test City Name A: Passed"
       else "Test City Name A: Failed. Expected " ++ show expected ++ ", got " ++ show result

testCityDistanceAtoB = 
    let expected = 5.0
        result = distanceC cityA cityB
    in if result == expected 
       then "Test City Distance A to B: Passed"
       else "Test City Distance A to B: Failed. Expected " ++ show expected ++ ", got " ++ show result

testQualityHighCapacity = 
    let expected = 5
        result = capacityQ qualityHigh
    in if result == expected 
       then "Test Quality High Capacity: Passed"
       else "Test Quality High Capacity: Failed. Expected " ++ show expected ++ ", got " ++ show result

testQualityLowDelay = 
    let expected = 1.5
        result = delayQ qualityLow
    in if result == expected 
       then "Test Quality Low Delay: Passed"
       else "Test Quality Low Delay: Failed. Expected " ++ show expected ++ ", got " ++ show result

testLinkABConnectsA = 
    if connectsL cityA linkABHigh 
       then "Test Link AB Connects A: Passed"
       else "Test Link AB Connects A: Failed"

testLinkABDoesNotConnectC = 
    if not (connectsL cityC linkABHigh) 
       then "Test Link AB Does Not Connect C: Passed"
       else "Test Link AB Does Not Connect C: Failed"

testLinkABLinksAtoB = 
    if linksL cityA cityB linkABHigh 
       then "Test Link AB Links A to B: Passed"
       else "Test Link AB Links A to B: Failed"

testLinkABDelay = 
    let expected = 2.5
        result = delayL linkABHigh
    in if result == expected 
       then "Test Link AB Delay: Passed"
       else "Test Link AB Delay: Failed. Expected " ++ show expected ++ ", got " ++ show result

testTunnelABConnectsAtoB = 
    if connectsT cityA cityB tunnelAB 
       then "Test Tunnel AB Connects A to B: Passed"
       else "Test Tunnel AB Connects A to B: Failed"

testTunnelABDoesNotConnectAtoC = 
    if not (connectsT cityA cityC tunnelAB) 
       then "Test Tunnel AB Does Not Connect A to C: Passed"
       else "Test Tunnel AB Does Not Connect A to C: Failed"

testTunnelABUsesLinkABHigh = 
    if usesT linkABHigh tunnelAB 
       then "Test Tunnel AB Uses Link AB High: Passed"
       else "Test Tunnel AB Uses Link AB High: Failed"

testTunnelABDelay = 
    let expected = 10.0
        result = delayT tunnelAB
    in if result == expected 
       then "Test Tunnel AB Delay: Passed"
       else "Test Tunnel AB Delay: Failed. Expected " ++ show expected ++ ", got " ++ show result
