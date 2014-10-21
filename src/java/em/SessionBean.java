/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package em;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
public class SessionBean implements SessionBeanLocal {
    @PersistenceContext(unitName = "IS3261PU")
    private EntityManager em;
    
    
    public UserEntity addUser(String email, String password) {
        try {
            Query q = em.createQuery("select u from UserEntity u where u.email = ?1").setParameter(1, email);
            if (q.getResultList().isEmpty()) {
                UserEntity user = new UserEntity(email, password);
                em.persist(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean verifyPassword(String email, String password) {
        try {
            Query q = em.createQuery("select u from UserEntity u where u.email = ?1 and u.password = ?2")
                    .setParameter(1, email)
                    .setParameter(2, password);
            if (!q.getResultList().isEmpty()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Event addEvent(Long userId, String title) {
        try {
            UserEntity user = em.find(UserEntity.class, userId);
            Event event = new Event();
            event.setTitle(title);
            event.setUser(user);
            em.persist(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Event searchEvent(Long eventId) {
        try {
            return em.find(Event.class, eventId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean editEventTitle(Long eventId, String title) {
        try {
            Event event = em.find(Event.class, eventId);
            event.setTitle(title);
            em.merge(event);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean deleteEvent(Long eventId) {
        try {
            em.remove(em.find(Event.class, eventId));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Question createQuestion(Long eventId, String title, String type) {
        try {
            Event event = em.find(Event.class, eventId);
            Question question = new Question();
            question.setType(title);
            question.setType(type);
            if (type.equals("MCQ")) {
                MCQ mcq = new MCQ();
                question.setMcq(mcq);
                em.persist(question);
                mcq.setQuestion(question);
                em.persist(mcq);
            }
            return question;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean editQuestionTitle(Long questionId, String title) {
        try {
            Question question = em.find(Question.class, questionId);
            question.setTitle(title);
            em.merge(question);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Question getQuestionById(Long questionId) {
        try {
            return em.find(Question.class, questionId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean deleteQuestion(Long QuestionId) {
        try {
            em.remove(em.find(Question.class, QuestionId));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Option addOptionToMCQ(Long mcqId, String abcd, String optionText, Boolean isAnswer) {
        try {
            Query q = em.createQuery("select op from Option op where op.mcq.id = ?1 and op.abcd = ?2")
                    .setParameter(1, mcqId)
                    .setParameter(2, abcd);
            if (q.getResultList().isEmpty()) {
                MCQ mcq = em.find(MCQ.class, mcqId);
                Option option = new Option(abcd, optionText, isAnswer);
                option.setMcq(mcq);
                em.persist(option);
                return option;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean deleteOption(Long optionId) {
        try {
            em.remove(em.find(Option.class, optionId));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean removeUser(Long userId) {
        try {
            em.remove(em.find(UserEntity.class, userId));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // try{}catch(Exception ex){ex.printStackTrace();}

    public void persist(Object object) {
        em.persist(object);
    }
}
