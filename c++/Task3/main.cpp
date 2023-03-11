#include "WaveHeadParser.h"
#include "ProgramContext.h"
#include "SoundProcessor.h"
//#include <fstream>
//#include <iostream>
#include <strstream>



int main(int argc, char **argv) {
    if (argc == 1) return 0;
    try {
        soundProcessor::ProgramContext context(argc, argv);

        soundProcessor::SoundProcessor processor;
        processor.makeTransformations(context);
    } catch (const std::exception &exception) {
        std::cerr << exception.what() << std::endl;
    }
    return 0;
}
