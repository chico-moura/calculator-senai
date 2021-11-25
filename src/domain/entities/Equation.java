package domain.entities;


import domain.repositories.EquationElementRepository;
import domain.services.AddNumberToRepositoryService;
import domain.services.AddOperationToRepositoryService;
import domain.valueObjects.Dot;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;


public class Equation {
    private final EquationElementRepository elementRepository;
    private final AddNumberToRepositoryService addNumberToRepositoryService;
    private final AddOperationToRepositoryService addOperationToRepositoryService;
    
    public Equation(){
        elementRepository = new EquationElementRepository();
        addNumberToRepositoryService = new AddNumberToRepositoryService(elementRepository);
        addOperationToRepositoryService = new AddOperationToRepositoryService(elementRepository);
    }
    
    public void addElement(Number element) {
        addNumberToRepositoryService.execucte(element);
    }
    
    public void addElement(Operation element) {
        addOperationToRepositoryService.execute(element);
    }
    
    public void addElement(Percent element) {
        if (elementRepository.hasAtLeastThree()) {
            EquationElement[] lastThreeElements = elementRepository.fetchLastThree();
            if (element.canBePlacedAfterThreeElements(lastThreeElements)){
                elementRepository.add(element);
            }
        }
    }
    
    public void addElement(Dot element) {
        
    }
    
    public void reset() {
        elementRepository.clear();
    }
    
    @Override
    public String toString() {
        String result = "";
        for (EquationElement element : elementRepository.fetchAll()) {
            result += element.toString() + " ";
        }
        return result;
    }    
}
