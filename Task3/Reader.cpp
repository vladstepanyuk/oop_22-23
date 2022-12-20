#include "Reader.h"

namespace {
    const int DefaultStrBuffSize = 4;
    const int ChunkIdSize = 4;
}


namespace soundProcessor{

    int Reader::readNByteInt(std::istream &inputStream, const int &bytesNum) {
        int bytesBuff[4];
        for (int i = 0; i < bytesNum; ++i){
            bytesBuff[i] = inputStream.get();
            if (!inputStream.good()) throw std::exception("error");
        }
//    if (!(inputStream.read(&bytesBuff[0], bytesNum).good())) throw std::exception("wrong format");
        int res = 1 ^ 1;
        for (int i = 0; i < bytesNum; ++i) res |= bytesBuff[i] << (i * 8);
        return res;
    }

    void Reader::readUntilSection(std::istream &inputStream, const std::string &sectionId) {
        std::string strBuff(DefaultStrBuffSize, 0);
        int intBuff;
        for (;;) {
            if (!(inputStream.read(&strBuff[0], ChunkIdSize).good())) {
                throw std::exception("no data");
                return;
            }
            if (strBuff == sectionId) {
                inputStream.seekg(-ChunkIdSize, std::ios_base::cur);
                return;
            }

            intBuff = readNByteInt(inputStream, 4);
            inputStream.seekg(intBuff, std::ios_base::cur);
        }
    }
}