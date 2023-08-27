import Control.Exception
import System.IO.Unsafe
import Point
import Quality
import City
import Link
import Tunel
import Region
import GHC.Exts.Heap (GenClosure(link))

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
link3 = newL mendoza sanJuan highQuality
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
regionNew2 = newR
region1 = foundR regionNew1 buenosAires

region2 = foundR regionNew2 mendoza

 
testing :: [Bool]
testing = [
    -- Point
    newP 5 8 == point1,
    newP 6 9 == point2,
    newP 7 13 == point3,
    newP 6 9 == point4,

    difP point1 point2 == 1.41421356237,
    difP point2 point4 == 0.0,

    -- Quality
    newQ "High" 10 10.2 == highQuality,
    newQ "Low" 5 2.6 == lowQuality,

    capacityQ highQuality == 10,
    capacityQ lowQuality == 5,

    delayQ highQuality == 10.2,
    delayQ lowQuality == 2.6,

    -- City
    newC "Buenos Aires" point1 == buenosAires,
    newC "Mendoza" point3 == mendoza,

    nameC buenosAires == "Buenos Aires",
    nameC sanLuis == "San Luis",

    distanceC buenosAires sanLuis == 1.41421356237,

    -- Link
    newL buenosAires cordoba highQuality == link1,
    -- newL cordoba sanLuis highQuality == error "Cannnot create a link with same coordinates",

    connectsL buenosAires link1,
-- 19
    not(linksL buenosAires mendoza link1),
    linksL buenosAires cordoba link1,
    linksL cordoba buenosAires link1,
    -- linksL mendoza mendoza link1 == error "Cities cannot be the same",

    capacityL link1 == 10,
    
    delayL link1 == 14.424978,

    -- Tunel
    newT [link1, link2, link3] == tunnel1,
    --newT [link1, link2, link5] == error "Cannnot create a link with same coordinates",

    connectsT buenosAires sanJuan tunnel1,
    not(connectsT buenosAires cordoba tunnel1),

    usesT link1 tunnel1,
    not(usesT link4 tunnel1),

    delayT tunnel1 == 61.921675,

    -- Region
    newR == regionNew1,
    newR == regionNew2,

    foundR regionNew1 buenosAires == region1,
    foundR regionNew2 mendoza == region2






    -- Region module tests
    --testF (foundR region1 buenosAires == error "The City is already in the region")
    
    ]
showResults = zipWith (\ i r -> "Test " ++ show i ++ ": " ++ show r) [1..] testing