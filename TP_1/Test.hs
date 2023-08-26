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
regionNew1 = newR
region2 = newR
region1 = foundR regionNew1 buenosAires

testing :: [Bool]
testing = [
    -- Points
    testF (newP 5 8 == point1),
    --point2 = newP 6 9 
   -- point3 = newP 7 13 
    --point4 = newP 6 9 

    -- Link module tests
    testF (newL sanLuis cordoba highQuality == error "Cannot create a link between the same city"),
    testF (newL buenosAires buenosAires highQuality == error "Cannot create a link between the same city"),

    testF (linksL sanLuis cordoba link2 == error "Cities cannot be the same"),
    testF (linksL buenosAires buenosAires link5 == error "Cities cannot be the same")

    -- Region module tests
    --testF (foundR region1 buenosAires == error "The City is already in the region")
    
    ]