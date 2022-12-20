#include "SoundProcessor.h"

namespace {
    const std::string TmpFileName1 = (std::filesystem::temp_directory_path()).string() + "\\SPTmp1.wav";
    const std::string TmpFileName2 = (std::filesystem::temp_directory_path()).string() + ".\\SPTmp2.wav";
    const int TmpFilesCount = 2;
    const int BuffSize = 100;
    const char CharSpace = ' ';
}

namespace soundProcessor {

    std::pair<std::string, std::vector<int>> parsConfigLine(const std::string &strBuff) {
        std::strstream strstream;
        strstream << strBuff;
        std::pair<std::string, std::vector<int>> commandArgsPair;
        strstream >> commandArgsPair.first;
        int tmp;
        while ((strstream >> tmp).good()) commandArgsPair.second.push_back(tmp);
        return commandArgsPair;
    }

    void SoundProcessor::makeTransformations(ProgramContext &context) {
        std::vector<std::string> tmpFilesNames = {TmpFileName1, TmpFileName2};
        std::string strBuff(BuffSize, 0);
        int numResInTmp = 0;

        if (context.returnConfigStream()->getline(&strBuff[0], BuffSize, '\n').eof())
            throw std::exception("empty config");
        std::pair<std::string, std::vector<int>> commandArgsPair = parsConfigLine(strBuff);

        Factory factory;
        Converter *converter = factory.newConverter(commandArgsPair.first);
        std::ofstream res(TmpFileName1, std::ios_base::binary);
        converter->convert(commandArgsPair.second, context, *context.returnInputStream(0), res);
        delete converter;

        res.close();

        std::ifstream prevRes;
        while (true) {
            if (context.returnConfigStream()->getline(&strBuff[0], BuffSize, '\n').eof()) break;
            commandArgsPair = parsConfigLine(strBuff);
            prevRes.open(tmpFilesNames[numResInTmp % tmpFilesNames.size()], std::ios_base::binary);
            res.open(tmpFilesNames[((numResInTmp + 1) % tmpFilesNames.size())], std::ios_base::binary);

            converter = factory.newConverter(commandArgsPair.first);

            converter->convert(commandArgsPair.second, context, prevRes, res);

            delete converter;

            res.close();
            prevRes.close();
            numResInTmp = (numResInTmp + 1) % tmpFilesNames.size();
        }
        prevRes.open(tmpFilesNames[numResInTmp], std::ios_base::binary);
        *context.returnOutputStream() << prevRes.rdbuf();
        prevRes.close();
        for (auto &fileName: tmpFilesNames)
            if (std::remove(fileName.c_str()))
                throw std::exception("cant delete tmp File");

    }
}