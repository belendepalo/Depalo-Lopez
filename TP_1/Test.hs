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

-- Definición de puntos de prueba
point1 = newP 5 8
point2 = newP 6 9
point3 = newP 7 13
point4 = newP 6 9
point5 = newP 4 15

-- Definición de calidades de prueba
highQuality = newQ "High" 10 10.2
lowQuality = newQ "Low" 5 2.6

-- Definición de ciudades de prueba
buenosAires = newC "Buenos Aires" point1
cordoba = newC "Cordoba" point2
mendoza = newC "Mendoza" point3
sanLuis = newC "San Luis" point4
sanJuan = newC "San Juan" point5

-- Definición de enlaces de prueba
link1 = newL buenosAires cordoba highQuality
link2 = newL cordoba mendoza lowQuality
link3 = newL mendoza sanJuan highQuality
link4 = newL mendoza buenosAires lowQuality
link5 = newL cordoba sanLuis lowQuality
link6 = newL sanJuan buenosAires highQuality

-- Definición de túneles de prueba
tunnel1 = newT [link1, link2, link3]
tunnel2 = newT [link1, link2, link5]
tunnel3 = newT [link1, link4, link3]
tunnel4 = newT [link4, link2, link3]

-- Definición de regiones de prueba
emptyRegion = newR
regionWithBuenosAires = foundR emptyRegion buenosAires
regionWithBuenosAiresCordoba = foundR regionWithBuenosAires cordoba
regionWithBuenosAiresCordobaMendoza = foundR regionWithBuenosAiresCordoba mendoza
regionWithAllCities = foundR regionWithBuenosAiresCordobaMendoza sanJuan
regionWithLink1 = linkR regionWithBuenosAiresCordoba buenosAires cordoba highQuality
regionWithLinks1To3 = linkR (linkR (linkR regionWithAllCities buenosAires cordoba highQuality) cordoba mendoza lowQuality) mendoza sanJuan highQuality
regionWithTunnel1 = tunelR regionWithLinks1To3 [buenosAires, sanJuan]

-- Lista de pruebas
testing :: [Bool]
testing = [
 
    -- Tests para Punto
    newP 5 8 == point1, -- Verifica creación de punto
    newP 6 9 == point2, 
    newP 7 13 == point3, 
    newP 6 9 == point4, 

    difP point1 point2 == 1.41421356237, -- Verifica diferencia entre dos puntos
    difP point2 point4 == 0.0, -- Verifica diferencia entre dos puntos con mismas coordenadas

    -- Tests para Calidad
    newQ "High" 10 10.2 == highQuality, -- Verifica creación de calidad
    newQ "Low" 5 2.6 == lowQuality, 

    capacityQ highQuality == 10, -- Verifica capacidad de calidad alta
    capacityQ lowQuality == 5, -- Verifica capacidad de calidad baja

    delayQ highQuality == 10.2, -- Verifica demora en calidad alta
    delayQ lowQuality == 2.6, -- Verifica demora en calidad baja

    -- Tests para Ciudad
    newC "Buenos Aires" point1 == buenosAires, -- Verifica creación de ciudad
    newC "Mendoza" point3 == mendoza, 

    nameC buenosAires == "Buenos Aires", -- Verifica obtener nombre de ciudad
    nameC sanLuis == "San Luis", 

    distanceC buenosAires sanLuis == 1.41421356237, -- Verifica distancia entre dos ciudades

    -- Tests para Enlace
    newL buenosAires cordoba highQuality == link1, -- Verifica creación de enlace

    connectsL buenosAires link1, -- Verifica si una ciudad se conecta con un enlace
-- 19
    not (linksL buenosAires mendoza link1), -- Verifica si dos ciudades están enlazadas por un enlace
    linksL buenosAires cordoba link1, 
    linksL cordoba buenosAires link1, 

    capacityL link1 == 10, -- Verifica capacidad de enlace

    delayL link1 == 14.424978, -- Verifica demora de enlace

    -- Tests para Túnel
    newT [link1, link2, link3] == tunnel1, -- Verifica creación de túnel

    connectsT buenosAires sanJuan tunnel1, -- Verifica si dos ciudades están conectadas por un túnel
    not (connectsT buenosAires cordoba tunnel1), -- Verifica si dos ciudades no están conectadas por un túnel

    usesT link1 tunnel1, -- Verifica si un enlace es utilizado por un túnel
    not (usesT link4 tunnel1), -- Verifica si un enlace no es utilizado por un túnel

    delayT tunnel1 == 61.921675, -- Verifica demora de túnel

   -- Tests para Región
    emptyRegion == newR, -- Verifica creación de región vacía
    regionWithBuenosAires == foundR newR buenosAires, -- Verifica fundación de ciudad en región
    regionWithBuenosAiresCordoba == foundR regionWithBuenosAires cordoba, -- Verifica fundación de ciudad en región
    regionWithLink1 == linkR regionWithBuenosAiresCordoba buenosAires cordoba highQuality, -- Verifica creación de enlace en región
    regionWithTunnel1 == tunelR regionWithLinks1To3 [buenosAires, sanJuan] -- Verifica creación de túnel en región
    ]
