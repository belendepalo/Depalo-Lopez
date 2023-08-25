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


-- Qualities
highQuality = newQ "High" 10 10.2
lowQuality = newQ "Low" 5 2.6

-- Cities
buenosAires = newC "Buenos Aires" point1
cordoba = newC "Cordoba" point2
mendoza = newC "Cordoba" point3
sanLuis = newC "Cordoba" point4

-- Links
link1 = newL buenosAires cordoba highQuality
link2 = newL cordoba mendoza lowQuality
link3 = newL mendoza cordoba highQuality
link4 = newL mendoza buenosAires lowQuality
link5 = newL cordoba sanLuis lowQuality -- oh nooo
link6 = newL sanLuis buenosAires highQuality



testing :: [Bool]
testing = [
    -- Point module tests
    testF (newP 2 3),
    testF (newP 5 7),
    testF (difP point1 point2),

    -- Quality module tests
    testF (newQ "High" 10 10.2),
    testF (newQ "Low" 5 2.6),
    testF (newQ "Low" 10 2.6),
    testF (capacityQ highQuality),
    testF (capacityQ lowQuality),
    testF (delayQ highQuality),
    testF (delayQ lowQuality),

    -- City module tests
    testF (newC "Buenos Aires" point1),
    testF (newC "Buenos Aires" point2),
    testF (nameC buenosAires),
    testF (nameC cordoba),
    testF (distanceC buenosAires cordoba),

    -- Link module tests
    testF (newL buenosAires cordoba highQuality),
    testF (newL sanLuis cordoba highQuality),
    testF (connectsL sanLuis link1),
    testF (connectsL sanLuis link2),
    testF (connectsL sanLuis link3),
    testF (connectsL sanLuis link4),
    testF (connectsL sanLuis link5),
    testF (linksL sanLuis cordoba link5),
    testF (linksL buenosAires cordoba link1),
    testF (linksL sanLuis buenosAires link4),
    testF (linksL sanLuis cordoba link5),
    testF (capacityL link4),
    testF (capacityL link5),
    testF (capacityL link3),
    testF (capacityL link1),

    -- Tunel module tests
    testF (newT [link1, link2, link3]),
    testF (newT [link4, link2, link2]),
    testF (newT [link1, link2, link5])

    ]

showResults = map (\(i, r) -> "Test " ++ show i ++ ": " ++ show r) (zip [1..] testing)