#pragma once
#include "ProgramContext.h"
#include "WaveHeadParser.h"
#include "Printer.h"


namespace soundProcessor {

    class Converter {
    public:
        virtual void convert(std::vector<int> args, ProgramContext &context, std::istream& prevRes, std::ostream& res) = 0;
        virtual ~Converter();
    };

    class MuteConverter : public Converter {
    public:
        void convert(std::vector<int> args, ProgramContext &context, std::istream& prevRes, std::ostream& res) override;
    };

    class MixConverter : public Converter {
    public:
        void convert(std::vector<int> args, ProgramContext &context, std::istream& prevRes, std::ostream& res) override;
    };

    class MultConverter : public Converter {
    public:
        void convert(std::vector<int> args, ProgramContext &context, std::istream& prevRes, std::ostream& res) override;
    };
} // soundProcessor

