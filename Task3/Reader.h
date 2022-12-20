#pragma once
#include <iostream>

namespace soundProcessor{
    class Reader {
    public:
        int readNByteInt(std::istream &inputStream, const int &bytesNum);

        void readUntilSection(std::istream &inputStream, const std::string &sectionId);
    };
}


