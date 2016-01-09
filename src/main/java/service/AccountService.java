package service;

import bean.UserProfile;

public interface AccountService {
    void addUser(UserProfile userProfile);
    UserProfile getUserByLogin(String login);
    UserProfile getUserBySessionId(String sessionId);
    void addSession(String sessionId, UserProfile userProfile);
    void deleteSession(String sessionId);
}
