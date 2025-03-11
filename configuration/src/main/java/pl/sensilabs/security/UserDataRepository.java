package pl.sensilabs.security;

public interface UserDataRepository {
  UserAccount loadUserByUsername(String username);
  UserAccount saveUserDetails(UserAccount userAccount);
  Boolean existsByUsername(String username);
}