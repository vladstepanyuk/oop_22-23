#include "ProgramContext.h"

namespace {
    const std::string DescDescription = "All options";
    const int MinFilesNum = 3;
    const int ConfigFileArgc = 0;
    const int OutputFileArgc = 1;
}

soundProcessor::ProgramContext::ProgramContext(int argc, char **argv) {
    thereIsC = false;
    po::options_description desc(DescDescription);
    std::string configFileName;
    std::string outputFileName;
    std::vector<std::string> inputFilesNames;
    desc.add_options()
            ("help,h", "Show options description")
            ("config,c", po::value<std::string>(&configFileName),
             "config file names")
            ("input,I", po::value<std::vector<std::string>>(&inputFilesNames),
             "input file names")
            ("output,o", po::value<std::string>(&outputFileName),
             "output file names");

    po::variables_map vm;
    po::store(po::command_line_parser(argc, argv).options(desc).run(), vm);
    po::notify(vm);

    if (vm.count("help")) std::cout << desc << std::endl;
    if(!vm.count("config")) return;
    else if(!vm.count("input") || !vm.count("output")) throw std::exception("no input or output files");

    auto *configFile = new std::ifstream();
    configFile->open(configFileName);
    if(!configFile->is_open()) throw std::exception("can't open config file");
    configStream = configFile;

    auto *outputFile = new std::ofstream();
    outputFile->open(outputFileName, std::ios_base::binary);
    if(!outputFile->is_open()) throw std::exception("can't open output file");
    outputStream = outputFile;

    for (int i = 0; i < inputFilesNames.size(); ++i) {
        auto *inputFile = new std::ifstream();
        inputFile->open(inputFilesNames[i], std::ios_base::binary);
        if(!inputFile->is_open()) throw std::exception("can't open input file");
        inputStreams.push_back(inputFile);
    }
    thereIsC = true;
}

soundProcessor::ProgramContext::~ProgramContext() {
    if (thereIsC){
        delete configStream;
        delete outputStream;
        for (auto & inputStream : inputStreams) delete inputStream;
    }


}

std::istream * soundProcessor::ProgramContext::returnInputStream(int N) {
    if (N >= inputStreams.size()) throw std::exception("out of bound");
    return inputStreams[N];
}

std::istream *soundProcessor::ProgramContext::returnConfigStream() {
    return configStream;
}

std::ostream * soundProcessor::ProgramContext::returnOutputStream() {
    return outputStream;
}
