package uk.gav.pun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gav.pun.entity.Pun;
import uk.gav.pun.repository.PunRepository;

@Service
public class PunServiceImpl implements PunService {
    @Autowired
    private Randomiser randomiser;

    @Autowired
    private PunRepository punRepository;

    @Override
    public void addPun(Pun pun) {
        this.punRepository.save(pun);
    }

    @Override
    public Pun getPun() {
        //Get the number of records available
        long punLimit = this.punRepository.count();

        long rand = this.randomiser.getValue(0, punLimit);

        Pun pun = this.punRepository.getReferenceById(rand);

        return pun;
    }

}
