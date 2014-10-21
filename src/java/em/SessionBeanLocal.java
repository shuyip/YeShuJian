/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package em;

import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface SessionBeanLocal {
    public UserEntity addUser(String email, String password);
    public Boolean verifyPassword(String email, String password);
    public Event addEvent(Long userId, String title);
    public Event searchEvent(Long eventId);
    public Boolean editEventTitle(Long eventId, String title);
    public Boolean deleteEvent(Long eventId);
    public Question createQuestion(Long eventId, String title, String type);
    public Boolean editQuestionTitle(Long questionId, String title);
    public Question getQuestionById(Long questionId);
    public Boolean deleteQuestion(Long QuestionId);
    public Option addOptionToMCQ(Long mcqId, String abcd, String optionText, Boolean isAnswer);
    public Boolean deleteOption(Long optionId);
    
    public Boolean removeUser(Long userId);
}
