#include "LifeConway.h"


using namespace std;
using namespace lifeConway;

int main(int argc, char **argv) {
    LifeConway x(argc, argv);
    OfflineInterface offlineInterface;
    OnlineInterface onlineInterface;
    if (argc == 4) x.setGameMode(&offlineInterface);
    else x.setGameMode(&onlineInterface);
    x.startGame();
}
