#include "Reader.h"

namespace {
    const int DefaultStrBuffSize = 4;
    const int ChunkIdSize = 4;
    const int SampleSize = 2;
}


namespace soundProcessor{

    int Reader::readNByteInt(std::istream &inputStream, const int &bytesNum) {
        int bytesBuff[4];
        for (int i = 0; i < bytesNum; ++i){
            bytesBuff[i] = inputStream.get();
            if (!inputStream.good()) throw std::exception("error");
        }
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

    std::vector<short> Reader::readNSamples(std::istream &inputStream, int N) {
        std::vector<short int> result(N);
        std::string stringBuff(N*2, 0);
        if (!inputStream.read(&stringBuff[0], N*2).good()) throw std::exception("can't read1");
        std::stringstream strStream;
        strStream << stringBuff;
        for (int i = 0; i < N; ++i) result[i] = readNByteInt(strStream, SampleSize);
        return result;
    }

}