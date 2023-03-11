#include <iostream>
#include "CSVParser.h"
#include "TuplePrinter.h"
#include <fstream>

using namespace tuplePrinter;



int main(){
    try{
        std::ifstream stream("in.txt");
        parser::CSVParser< std::string, int> parser(stream, 0);
        for (auto &i : parser){
            std::cout << i << std::endl;
        }

    } catch (std::exception & exception){
        std::cerr << exception.what() << std::endl;
    }


}