#include "LifeConway.h"


using namespace std;

int main(int argc, char **argv) {
    try {
        lifeConway::ProgramContext context(argc, argv);
        lifeConway::LifeConway game(context);
        lifeConway::OfflineInterface offlineInterface;
        lifeConway::OnlineInterface onlineInterface;
        if (!context.IsOnline()) game.setGameMode(&offlineInterface);
        else game.setGameMode(&onlineInterface);
        game.startGame();
    } catch (std::exception & exception){
        std::cerr << exception.what();
    }
    return 0;
}
