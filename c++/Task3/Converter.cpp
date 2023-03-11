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
    const int SampleBuffSize = 1024;
    const int MultArgc = 3;
    const int MuteArgc = 2;
    const int MixArgc = 2;

}

namespace soundProcessor {
    void
    MuteConverter::convert(std::vector<int> args, ProgramContext &context, std::istream &prevRes, std::ostream &res) {
        static WaveHeadParser parser;
        static Reader reader;
        static Printer printer;

        if (args.size() < MuteArgc) throw std::exception("wrong args number");


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

        if (args.size() < MixArgc) throw std::exception("wrong args number");


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
        std::vector<short int> buffSample1(SampleBuffSize);
        std::vector<short int> buffSample2(SampleBuffSize);



        for (int i = 0; i < (minData/ShortIntSize - args[MixArgcFirstSecond] * StandardHz)/SampleBuffSize; i += 1) {
            buffSample1 = reader.readNSamples(prevRes, SampleBuffSize);
            buffSample2 = reader.readNSamples(input, SampleBuffSize);
            for (int l = 0; l < SampleBuffSize; ++l)
                buffSample1[l] = (buffSample1[l] + buffSample2[l])/2;

            printer.printNSamples(res, buffSample1, SampleBuffSize);
        }

        if ((minData/ShortIntSize - args[MixArgcFirstSecond] * StandardHz)%(SampleBuffSize) != 0) {
            int lastSamplesNum = (minData/ShortIntSize - args[MixArgcFirstSecond] * StandardHz)%(SampleBuffSize);
            buffSample1 = reader.readNSamples(prevRes, lastSamplesNum);
            buffSample2 = reader.readNSamples(input, lastSamplesNum);
            for (int l = 0; l < lastSamplesNum; ++l)
                buffSample1[l] = (buffSample1[l] + buffSample2[l])/2;

            printer.printNSamples(res, buffSample1, lastSamplesNum);
        }
        if (data1Size > minData) printer.printNBytesFromIntoOut(prevRes, res, data1Size - minData);

    }

    void
    MultConverter::convert(std::vector<int> args, ProgramContext &context, std::istream &prevRes, std::ostream &res) {
        static WaveHeadParser parser;
        static Printer printer;
        static Reader reader;

        if (args.size() < MultArgc) throw std::exception("wrong args number");

        if (args[MultArgcFirstSecond] > args[MultArgcLastSecond]) throw std::exception("wrong format config");

        prevRes.seekg(0, std::ios_base::beg);
        parser.pars(prevRes);
        prevRes.seekg(ChunkIdSize, std::ios_base::cur);
        int dataSize = reader.readNByteInt(prevRes, IntSize);

        if (args[MultArgcLastSecond] * StandardHz * BytesPerSample > dataSize) throw std::exception("wrong format");

        printer.printWaveHead(res, dataSize);
        printer.printChunckId(res, DataId);
        printer.printNByteInt(res, dataSize, IntSize);
        printer.printNBytesFromIntoOut(prevRes, res, args[MuteArgcFirstSecond] * StandardHz * BytesPerSample);

        std::vector<short int> buffSample(SampleBuffSize);

        for (int i = 0; i < (args[MultArgcLastSecond] - args[MuteArgcFirstSecond]) * StandardHz / SampleBuffSize; i += 1) {
            buffSample = reader.readNSamples(prevRes, SampleBuffSize);
            for (int j = 0; j < SampleBuffSize; ++j)
                buffSample[j] *= args[MultArgcMultCoef];
            printer.printNSamples(res, buffSample, SampleBuffSize);
        }

        if ((args[MultArgcLastSecond] - args[MuteArgcFirstSecond]) * StandardHz % SampleBuffSize != 0){
            buffSample = reader.readNSamples(prevRes, (args[MultArgcLastSecond] - args[MuteArgcFirstSecond]) * StandardHz % SampleBuffSize);
            for (int j = 0; j < (args[MultArgcLastSecond] - args[MuteArgcFirstSecond]) * StandardHz % SampleBuffSize; ++j)
                buffSample[j] *= args[MultArgcMultCoef];
            printer.printNSamples(res, buffSample, (args[MultArgcLastSecond] - args[MuteArgcFirstSecond]) * StandardHz % SampleBuffSize);
        }

        printer.printNBytesFromIntoOut(prevRes, res, dataSize - args[MultArgcLastSecond] * StandardHz * BytesPerSample);

    }

    Converter::~Converter() = default;
} // soundProcessor