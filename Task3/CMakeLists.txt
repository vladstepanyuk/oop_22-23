cmake_minimum_required(VERSION 3.22)
project(Task3)
#project(Task3_TEST)


set(CMAKE_CXX_STANDARD 17)

set(Sources main.cpp WaveHeadParser.cpp SoundProcessor.cpp ProgramContext.cpp Converter.cpp Printer.cpp Factory.cpp Reader.cpp )
set(Headers WaveHeadParser.h SoundProcessor.h ProgramContext.h Converter.h Printer.h  Factory.h  Reader.h)
set(Sources_test)
set(Headers_test)


add_subdirectory(lib/googletest-main)

include_directories(lib/googletest-main/googletest/include)

add_executable(Task3 ${Sources} ${Headers})
#add_executable(Task3_TEST ${Sources_test})

set(Boost_USE_STATIC_LIBS ON)

set(Boost_ROOT_DIR  C:/Users/io/Desktop/oop_22-23/Task3/lib/boost_1_80_0)
#SET(CMAKE_INCLUDE_PATH ${CMAKE_INCLUDE_PATH} "./lib/boost_1_80_0")
#SET(CMAKE_LIBRARY_PATH ${CMAKE_LIBRARY_PATH} "./lib/boost_1_80_0/libx64/lib")
set(Boost_INCLUDE_DIR C:/Users/io/Desktop/oop_22-23/Task3/lib/boost_1_80_0)
set(Boost_LIBRARY_DIR C:/Users/io/Desktop/oop_22-23/Task3/lib/boost_1_80_0/bin/x86/lib)
#set(Boost_LIBRARY_DIR c:/mingw/lib/)
find_package(Boost COMPONENTS program_options REQUIRED)

target_include_directories(Task3 PUBLIC ${Boost_INCLUDE_DIR})

target_link_libraries(Task3 gtest gtest_main ${Boost_LIBRARIES})
#target_link_libraries(Task3_TEST gtest gtest_main)
