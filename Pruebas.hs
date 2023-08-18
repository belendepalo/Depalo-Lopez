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

-- Pruebas para Point
testDistanceAtoB :: String
testDistanceAtoB = if difP pointA pointB == 5.0 then "Passed" else "Failed"
testDistanceBtoC :: String
testDistanceBtoC = if difP pointB pointC == 8.246211251235321 then "Passed" else "Failed"

-- Pruebas para City
testCityNameA :: String
testCityNameA = if nameC cityA == "CityA" then "Passed" else "Failed"
testCityDistanceAtoB :: String
testCityDistanceAtoB = if distanceC cityA cityB == 5.0 then "Passed" else "Failed"

-- Pruebas para Quality
testQualityHighCapacity :: String
testQualityHighCapacity = if capacityQ qualityHigh == 5 then "Passed" else "Failed"
testQualityLowDelay :: String
testQualityLowDelay = if delayQ qualityLow == 1.5 then "Passed" else "Failed"

-- Pruebas para Link
testLinkABConnectsA :: String
testLinkABConnectsA = if connectsL cityA linkABHigh then "Passed" else "Failed"
testLinkABDoesNotConnectC :: String
testLinkABDoesNotConnectC = if not (connectsL cityC linkABHigh) then "Passed" else "Failed"
testLinkABLinksAtoB :: String
testLinkABLinksAtoB = if linksL cityA cityB linkABHigh then "Passed" else "Failed"
testLinkABDelay :: String
testLinkABDelay = if delayL linkABHigh == 2.5 then "Passed" else "Failed"

-- Pruebas para Tunel
testTunnelABConnectsAtoB :: String
testTunnelABConnectsAtoB = if connectsT cityA cityB tunnelAB then "Passed" else "Failed"
testTunnelABDoesNotConnectAtoC :: String
testTunnelABDoesNotConnectAtoC = if not (connectsT cityA cityC tunnelAB) then "Passed" else "Failed"
testTunnelABUsesLinkABHigh :: String
testTunnelABUsesLinkABHigh = if usesT linkABHigh tunnelAB then "Passed" else "Failed"
testTunnelABDelay :: String
testTunnelABDelay = if delayT tunnelAB == 4.0 then "Passed" else "Failed"