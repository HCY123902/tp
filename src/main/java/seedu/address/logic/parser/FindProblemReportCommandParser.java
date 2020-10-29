package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindProblemReportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.problem.DescriptionContainsKeywordsPredicate;




public class FindProblemReportCommandParser implements Parser<FindProblemReportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindProblemReportCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProblemReportCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindProblemReportCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
