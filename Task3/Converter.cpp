//
// Created by io on 11.12.2022.
//

#include "Converter.h"

namespace {
    const int BytesPerSample = 2;
    const char silence = 0;
    const std::string DataId = "data";
    const int IntSize = 4;
    const int ShortIntSize = 2;
    const int MuteArgcFirstSecond = 0;
    const int MuteArgcLastSecond = 1;
    const int MixArgcFileToMix = 0;
    const int MixArgcFirstSecond = 1;
    const int MultArgcFirstSecond = 0;
    const int MultArgcLastSecond = 1;
    const int MultArgcMultCoef = 2;
}

namespace soundProcessor {
    void
    MuteConverter::convert(std::vector<int> args, ProgramContext &context, std::istream &prevRes, std::ostream &res) {
        static WaveHeadParser parser;
        static Reader reader;
        static Printer printer;

        if (args[MuteArgcFirstSecond] > args[MuteArgcLastSecond]) throw std::exception("wrong format config");


        prevRes.seekg(0, std::ios_base::beg);
        parser.pars(prevRes);
        prevRes.seekg(ChunkIdSize, std::ios_base::cur);
        int dataSize = reader.readNByteInt(prevRes, IntSize);

        if (args[MuteArgcLastSecond] * StandardHz * BytesPerSample > dataSize)
            throw std::exception("wrong format");

        printer.printWaveHead(res, dataSize);
        printer.printChunckId(res, DataId);
        printer.printNByteInt(res, dataSize, IntSize);
//        printer.printInt(res, dataSize);
        printer.printNBytesFromIntoOut(prevRes, res, args[MuteArgcFirstSecond] * StandardHz * BytesPerSample);


        printer.printNCharacters(res,
                                 (args[MuteArgcLastSecond] - args[MuteArgcFirstSecond]) * StandardHz * BytesPerSample,
                                 silence);
        prevRes.seekg((args[MuteArgcLastSecond] - args[MuteArgcFirstSecond]) * StandardHz * BytesPerSample,
                      std::ios_base::cur);

        printer.printNBytesFromIntoOut(prevRes, res, dataSize - args[MuteArgcLastSecond] * StandardHz * BytesPerSample);

    }

    void
    MixConverter::convert(std::vector<int> args, ProgramContext &context, std::istream &prevRes, std::ostream &res) {
        static WaveHeadParser parser;
        static Printer printer;
        static Reader reader;


        std::istream &input = *context.returnInputStream(args[MixArgcFileToMix]);
        prevRes.seekg(0, std::ios_base::beg);
        input.seekg(0, std::ios_base::beg);
        parser.pars(prevRes);
        parser.pars(input);
        prevRes.seekg(ChunkIdSize, std::ios_base::cur);
        input.seekg(ChunkIdSize, std::ios_base::cur);
        int data1Size = reader.readNByteInt(prevRes, IntSize);
        int data2Size = reader.readNByteInt(input, IntSize);
        int minData = data1Size < data2Size ? data1Size : data2Size;

        if (args[MixArgcFirstSecond] * StandardHz * BytesPerSample > minData) throw std::exception("wrong format");

        printer.printWaveHead(res, data1Size);
        printer.printChunckId(res, DataId);
//        printer.printInt(res, data1Size);
        printer.printNByteInt(res, data1Size, IntSize);
        printer.printNBytesFromIntoOut(prevRes, res, args[MixArgcFirstSecond] * StandardHz * BytesPerSample);
        input.seekg(args[MixArgcFirstSecond] * StandardHz * BytesPerSample, std::ios_base::cur);


        for (int i = args[MixArgcFirstSecond] * StandardHz * BytesPerSample; i < minData; i += ShortIntSize) {
            short int sample1 = reader.readNByteInt(prevRes, ShortIntSize);
            short int sample2 = reader.readNByteInt(input, ShortIntSize);
            short int newSample = (sample1 + sample2) / 2;

//            printer.printSample(res, newSample);
            printer.printNByteInt(res, newSample, ShortIntSize);
        }
        if (data1Size > minData) printer.printNBytesFromIntoOut(prevRes, res, data1Size - minData);

    }

    void
    MultConverter::convert(std::vector<int> args, ProgramContext &context, std::istream &prevRes, std::ostream &res) {
        static WaveHeadParser parser;
        static Printer printer;
        static Reader reader;

        if (args[MultArgcFirstSecond] > args[MultArgcLastSecond]) throw std::exception("wrong format config");

        prevRes.seekg(0, std::ios_base::beg);
        parser.pars(prevRes);
        prevRes.seekg(ChunkIdSize, std::ios_base::cur);
        int dataSize = reader.readNByteInt(prevRes, IntSize);

        if (args[MultArgcLastSecond] * StandardHz * BytesPerSample > dataSize) throw std::exception("wrong format");

        printer.printWaveHead(res, dataSize);
        printer.printChunckId(res, DataId);
        printer.printNByteInt(res, dataSize, IntSize);
//        printer.printInt(res, dataSize);
        printer.printNBytesFromIntoOut(prevRes, res, args[MuteArgcFirstSecond] * StandardHz * BytesPerSample);

        for (int i = 0; i < (args[MultArgcLastSecond] - args[MuteArgcFirstSecond]) * StandardHz; i += 1) {
            short int sample = reader.readNByteInt(prevRes, ShortIntSize);
//            printer.printSample(res, sample * args[MultArgcMultCoef]);
            printer.printNByteInt(res, sample * args[MultArgcMultCoef], ShortIntSize);
        }

        printer.printNBytesFromIntoOut(prevRes, res, dataSize - args[MultArgcLastSecond] * StandardHz * BytesPerSample);

    }

    Converter::~Converter() = default;
} // soundProcessor