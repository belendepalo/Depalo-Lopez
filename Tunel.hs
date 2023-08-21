{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use newtype instead of data" #-}

module Tunel ( Tunel(..), newT, connectsT, usesT, delayT )
   where

import City
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conecta estas dos ciudades distintas
connectsT c0 c1 (Tun links) =
   let (Lin cityA _ _) = head links ; (Lin _ cityB _) = last links
   in (cityA == c0 && cityB == c1) || (cityB == c0 && cityA == c1)

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT lin_0 (Tun links) = lin_0 `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum [delayL link | link <- links]