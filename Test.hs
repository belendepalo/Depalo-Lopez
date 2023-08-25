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

-- Qualities
highQuality = newQ "High" 10 10.2
lowQuality = newQ "Low" 5 2.6

-- Cities
buenosAires = newC "Buenos Aires" point1
cordoba = newC "Cordoba" point2

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
    testF (nameC cordoba)




    ]

showResults = map (\(i, r) -> "Test " ++ show i ++ ": " ++ show r) (zip [1..] testing)