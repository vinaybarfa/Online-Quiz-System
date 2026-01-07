package com.vinay.OnlineQuizapp.service;

import com.vinay.OnlineQuizapp.dto.AnswerSubmitionDTO;
import com.vinay.OnlineQuizapp.dto.QuestionDTO;
import com.vinay.OnlineQuizapp.entity.Question;
import com.vinay.OnlineQuizapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(question->modelMapper.map(question, QuestionDTO.class)).toList();
    }


    public List<QuestionDTO> getByCategory(String category) {
        List<Question> byCategory = questionRepository.findByCategory(category);
        return byCategory.stream().map(catego -> modelMapper.map(catego, QuestionDTO.class)).toList();
    }

    public QuestionDTO addQuestion(Question question){
        Question newQuestion = questionRepository.save(question);
        return modelMapper.map(newQuestion, QuestionDTO.class);
    }

    public List<QuestionDTO> addAllQuestion(List<Question> question){
        List<Question> newAddedQuestion = questionRepository.saveAll(question);
        return newAddedQuestion.stream().map(newQ -> modelMapper.map(newQ, QuestionDTO.class)).toList();
    }

    public void deleteQuestionById(Integer id) {
         questionRepository.deleteById(id);
    }

    public String submitAnswer(AnswerSubmitionDTO answerSubmitionDTO) {
        return questionRepository.findByRightAnswer(answerSubmitionDTO.getRightAnswer());
    }
}
