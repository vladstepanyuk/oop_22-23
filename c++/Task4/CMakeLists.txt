cmake_minimum_required(VERSION 3.16)
project(Task4)

set(CMAKE_CXX_STANDARD 17)
add_subdirectory(lib/googletest-main)

include_directories(lib/googletest-main/googletest/include)



add_executable(Task4 main.cpp CSVParser.h TuplePrinter.h)
