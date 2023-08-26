import Control.Exception
import System.IO.Unsafe
import Point
import Quality
import City
import Link
import Tunel
import Region

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()


-- Points
point1 = newP 5 8
point2 = newP 6 9
point3 = newP 7 13
point4 = newP 6 9
point5 = newP 4 15

-- Qualities
highQuality = newQ "High" 10 10.2
lowQuality = newQ "Low" 5 2.6

-- Cities
buenosAires = newC "Buenos Aires" point1
cordoba = newC "Cordoba" point2
mendoza = newC "Mendoza" point3 
sanLuis = newC "San Luis" point4 
sanJuan = newC "San Juan" point5

-- Links
link1 = newL buenosAires cordoba highQuality
link2 = newL cordoba mendoza lowQuality
link3 = newL mendoza cordoba highQuality
link4 = newL mendoza buenosAires lowQuality
link5 = newL cordoba sanLuis lowQuality
link6 = newL sanJuan buenosAires highQuality

-- Tunnels
tunnel1 = newT [link1, link2, link3]
tunnel2 = newT [link1, link2, link5]
tunnel3 = newT [link1, link4, link3]
tunnel4 = newT [link4, link2, link3]

-- Regions
region1 = newR [buenosAires, mendoza, sanLuis,cordoba] [link1, link2, link3] [tunnel1]
region2 = newR [buenosAires, mendoza, sanLuis] [link1, link2, link3, link5] [tunnel1]

testing :: [Bool]
testing = [

    -- Link module tests
    testF (newL sanLuis cordoba highQuality == error "Cannnot create a link between the same city"),
    testF (newL buenosAires buenosAires highQuality == error "Cannnot create a link between the same city"),

    testF (linksL sanLuis cordoba link2 == error "Cities cannot be the same"),
    testF (linksL buenosAires buenosAires link5 == error "Cities cannot be the same"),

    -- Region module tests
    testF (foundR region1 buenosAires  == error "The city is already in the Region"),
    testF (foundR region1 cordoba  == error "A city with the same coordinates already exists"),

    testF (linkR region1 cordoba cordoba == error "Cannot link a city to itself"),
    testF (linkR region2 sanJuan buenosAires == error "One or both cities are not in the region"),
    testF (linkR region1 cordoba sanLuis == error "One or both cities are not in the region"),
    testF (tunelR region1 [buenosAires, cordoba, mendoza] == error "At least two cities are required to create a tunnel"),
    testF (delayR region1 buenosAires mendoza == 24.6)
    ]