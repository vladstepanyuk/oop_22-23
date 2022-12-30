
#include "Printer.h"

namespace {
    const std::string RiffId = "RIFF";
    const std::string WaveFmtId = "WAVEfmt ";
    const unsigned char fmt[] = {0x10, 0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 0x00, 0x44, 0xAC, 0x00, 0x00, 0x88, 0x58,
                                 0x01, 0x00, 0x02, 0x00, 0x10, 0x00};
    const unsigned char oneByte = ~0;
    const int StandardStringBuffer = 1024;
    const int ShortIntSize = 2;
    const int BitsPerByte = 8;
    const int IntSize = 4;
}

namespace soundProcessor {
    void Printer::printWaveHead(std::ostream &outStream, int dataSize) {
        outStream << RiffId;
        printNByteInt(outStream, dataSize + WaveFmtId.size() + 20 + 8, IntSize);
        outStream << WaveFmtId;
        for (unsigned char i: fmt) {
            outStream.put(i);
        }
    }

    void Printer::printNBytesFromIntoOut(std::istream &inputStream, std::ostream &outStream, int N) {
        std::string str(StandardStringBuffer, 0);
        for (int i = 0; i < N / StandardStringBuffer; ++i) {
            if (!inputStream.read(&str[0], StandardStringBuffer).good()) throw std::exception("cant reed");
            if (!outStream.write(&str[0], StandardStringBuffer).good()) throw std::exception("cant write");
        }
        if (!inputStream.read(&str[0], N % StandardStringBuffer).good()) throw std::exception("cant reed");
        if (!outStream.write(&str[0], N % StandardStringBuffer).good()) throw std::exception("cant write");
    }

//    void Printer::printSample(std::ostream &output, short sample) {
//        static int one = static_cast<int>(oneByte);
//        for (int i = 0; i < ShortIntSize; ++i) {
//            int byte = (sample & (one << BitsPerByte * (i))) >> BitsPerByte * i;
//            if (output.put(byte).bad()) throw std::exception("error");
//        }
//    }

//    void Printer::printInt(std::ostream &output, int num) {
//        static int one = static_cast<int>(oneByte);
//        for (int i = 0; i < IntSize; ++i) {
//            int byte = (num & (one << 8 * (i))) >> 8 * i;
//            output.put(byte);
//        }
//    }

    void Printer::printChunckId(std::ostream &output, const std::string &chunckId) {
        output << chunckId;
    }

    void Printer::printNCharacters(std::ostream &outStream, int N, char character) {
        std::string str(1024, character);
        for (int i = 0; i < N / 1024; ++i) {
            if (!outStream.write(&str[0], 1024).good()) throw std::exception("cant write");
        }
        if (!outStream.write(&str[0], N % 1024).good()) throw std::exception("cant write");
    }

    void Printer::printNByteInt(std::ostream &output, unsigned long long int num, int N) {
        static auto one = static_cast<unsigned long long>(oneByte);
        for (int i = 0; i < N; ++i) {
            int byte = (num & (one << BitsPerByte * (i))) >> BitsPerByte * i;
            output.put(byte);
        }
    }


}