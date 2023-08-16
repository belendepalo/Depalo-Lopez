{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use newtype instead of data" #-}

module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import City
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT c0 c1 (Tun links) = (head links == c0 && last links == c1) || (head links  == c1 && last links  == c0) 

-- creo que va a ser util [x | x <- xs, ]

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT l_0 (Tun links) = l_0 `elem` links 

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum $ map delayL links 