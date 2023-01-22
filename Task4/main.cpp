#include <iostream>
#include "CSVParser.h"
#include "TuplePrinter.h"

using namespace tuplePrinter;

const std::string csv = "453,c,2.0\n789,j,9.0\n666,\",,97.0\n";


int main(){
    try{
        std::stringstream stream(csv);
        parser::CSVParser<int, char, double> parser(stream, 0);
        for (auto &i : parser){
            std::cout << i << std::endl;
        }

    } catch (std::exception & exception){
        std::cerr << exception.what() << std::endl;
    }


}