package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TrainService {
    private List<Train> trainList;
    private ObjectMapper objectMapper=new ObjectMapper();
    private static final String TRAIN_PATH="app/src/main/java/ticket/booking/localDb/trains.json";

    public TrainService() throws IOException {
        loadTrains();
    }
    //TrainService trainService=new TrainService();

    private void loadTrains() throws IOException {

        trainList = objectMapper.readValue(
                new File(TRAIN_PATH),
                new TypeReference<List<Train>>() {}
        );

    }
    private void saveTrains() throws IOException {

        objectMapper.writeValue(
                new File(TRAIN_PATH),
                trainList
        );

    }
    public List<Train> getAllTrains() {

        return trainList;

    }
    public List<Train> searchTrain(String source, String destination) {

        return trainList.stream()

                .filter(train ->{
                        List<String> stations=train.getStation();
                        int sourceindex=stations.indexOf(source);
                        int destinationindex=stations.indexOf(destination);

                        return sourceindex!=-1 &&
                                destinationindex !=-1 &&
                                sourceindex < destinationindex;
                    }).toList();

    }
    private List<List<Boolean>> seats;
     
    public List<List<Boolean>> fetchSeats(String trainId) {

        for (Train train : trainList) {

            if (train.getTrainId().equalsIgnoreCase(trainId)) {

                return train.getSeats();

            }

        }

        return null;

    }

    public boolean updateSeats(String trainId,int row,int col) throws IOException{
        for(Train train:trainList){
            if(train.getTrainId().equalsIgnoreCase(trainId)){
                train.getSeats().get(row).set(col,false);

                saveTrains();
                return true;
            }
        }
        return false;
    }
}
