module Main where

{-
el comando para compilar es: ghc -odir build/o_files -hidir build/hi_files -o pruebas main.hs
para ejecutarlo es: ./pruebas
y para las carpetas donde se guardan los archivos basura son los tres siguentes:
mkdir build
mkdir build/o_files
mkdir build/hi_files
-} 

import Test

main :: IO ()
main = do
    putStrLn "Running tests..."
    

