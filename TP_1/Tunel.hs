{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use newtype instead of data" #-}

module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import City
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool
connectsT c0 c1 (Tun links) = (connectsL c0 firstLink && connectsL c1 lastLink) || (connectsL c1 firstLink && connectsL c0 lastLink) 
   where
      firstLink = head links
      lastLink = last links

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT lin_0 (Tun links) = lin_0 `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum [delayL link | link <- links]
