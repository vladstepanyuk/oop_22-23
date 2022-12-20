//
// Created by io on 05.12.2022.
//

#include "WaveHeadParser.h"

namespace {
    const std::string RIFFID = "RIFF";
    const std::string WAVEID = "WAVE";
    const std::string FMTID = "fmt ";
    const std::string DataId = "data";
    const int DefaultStrBuffSize = 4;
    const short int WithoutCompressionCode = 1;
    const short int StandardNumberOfChannels = 1;
    const int FMTMinSize = 16;

    const int BitsPerSample = 16;
    const short int BitsPerByte = 8;

}
const int soundProcessor::StandardHz = 44100;
const int soundProcessor::ChunkIdSize = 4;


void soundProcessor::WaveHeadParser::pars(std::istream &inputStream) {
    std::string strBuff(DefaultStrBuffSize, 0);
    Reader reader;

    if (!(inputStream.read(&strBuff[0], ChunkIdSize).good() && strBuff == RIFFID)) throw std::exception("wrong format1");
    int intBuff;
    short int shortIntBuff;
    int sectionSize;
    sectionSize = reader.readNByteInt(inputStream, 4);
    if (!(inputStream.read(&strBuff[0], ChunkIdSize).good() && strBuff == WAVEID)) throw std::exception("wrong format2");

    reader.readUntilSection(inputStream, FMTID);

    if (!(inputStream.read(&strBuff[0], ChunkIdSize) && strBuff == FMTID)) throw std::exception("wrong format3");

    sectionSize = reader.readNByteInt(inputStream, 4);
    if (sectionSize < FMTMinSize) throw std::exception("wrong fmt size");

    shortIntBuff = reader.readNByteInt(inputStream, 2);
    if ( shortIntBuff != WithoutCompressionCode) throw std::exception("wrong compression code");

    shortIntBuff = reader.readNByteInt(inputStream, 2);
    if (shortIntBuff != StandardNumberOfChannels) throw std::exception("wrong channels num");

    intBuff = reader.readNByteInt(inputStream, 4);
    if (intBuff != StandardHz) throw std::exception("wrong Hz");

    static short int BlockAlign = (BitsPerSample / BitsPerByte) * StandardNumberOfChannels;
    static int AverageBytesPerSecond = BlockAlign * StandardHz;
    intBuff = reader.readNByteInt(inputStream, 4);
    if (intBuff != AverageBytesPerSecond) throw std::exception("wrong Avg bytes per second");
    shortIntBuff = reader.readNByteInt(inputStream, 2);
    if (shortIntBuff != BlockAlign) throw std::exception("wrong block align");
    inputStream.seekg(2, std::ios_base::cur);

    reader.readUntilSection(inputStream, DataId);
}
