cmake_minimum_required(VERSION 3.22)
project(Task2)
project(Task2_TEST)


set(CMAKE_CXX_STANDARD 23)

set(Sources main.cpp Field.cpp Field.h LifeConway.cpp LifeConway.h InputFileParser.cpp InputFileParser.h Interface.cpp Interface.h Printer.cpp Printer.h ProgramContext.cpp ProgramContext.h)
set(Sources_test  tests.cpp Field.cpp Field.h LifeConway.cpp LifeConway.h InputFileParser.cpp InputFileParser.h Interface.cpp Interface.h Printer.cpp Printer.h ProgramContext.cpp ProgramContext.h)

add_subdirectory(lib/googletest-main)

include_directories(lib/googletest-main/googletest/include)

add_executable(Task2 ${Sources})
add_executable(Task2_TEST ${Sources_test})

target_link_libraries(Task2 gtest gtest_main)
target_link_libraries(Task2_TEST gtest gtest_main)
