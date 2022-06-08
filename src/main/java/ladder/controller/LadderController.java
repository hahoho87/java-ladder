package ladder.controller;

import ladder.converter.ResultConverter;
import ladder.domain.*;
import ladder.dto.LadderResultDto;
import ladder.exception.InvalidInputSizeException;
import ladder.view.InputView;
import ladder.view.OutputView;

public class LadderController {

    private final InputView inputView;
    private final OutputView outputView;

    private LadderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public LadderController() {
        this(new InputView(), new OutputView());
    }

    public void startLadderGame() {
        Participants participants = inputView.inputParticipants();
        ExecutionResults executionResults = inputView.inputExecutionResults();

        validateInputSize(participants, executionResults);

        int maxLadderHeight = inputView.inputMaxLadderHeight();

        LadderGenerator ladderGenerator = new LadderGenerator(new RandomDirectionGenerateStrategy());
        Ladder ladder = ladderGenerator.createLadder(participants.size(), maxLadderHeight);
        outputView.printLadder(ladder, participants, executionResults);

        LadderResultDto ladderResultDto = ResultConverter.convertToResultDto(ladder, executionResults, participants);
        String participantForResult = inputView.inputParticipantForResult();

        if (participantForResult.equals("all")) {
            outputView.printAllParticipantResults(ladderResultDto);
            return;
        }

        outputView.printParticipantResult(ladderResultDto, participantForResult);

    }

    private void validateInputSize(Participants participants, ExecutionResults executionResults) {
        if (participants.size() != executionResults.size()) {
            throw new InvalidInputSizeException(participants.size(), executionResults.size());
        }
    }
}
