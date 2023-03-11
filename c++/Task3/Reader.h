#pragma once
#include <iostream>
#include <vector>
#include <sstream>

namespace soundProcessor{
    class Reader {
    public:
        int readNByteInt(std::istream &inputStream, const int &bytesNum);

        void readUntilSection(std::istream &inputStream, const std::string &sectionId);

        std::vector<short> readNSamples(std::istream &inputStream, int N);
    };
}


