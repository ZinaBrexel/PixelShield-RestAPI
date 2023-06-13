package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.dto.UserForm;
import fr.simplon.pixelshielrestapi.entity.UserProfile;
import fr.simplon.pixelshielrestapi.repository.UserProfileRepository;
import fr.simplon.pixelshielrestapi.service.UserProfilService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Controller
public class UsersController
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private UserProfilService userProfilService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    public UsersController(
            PasswordEncoder pPasswordEncoder,
            UserDetailsManager pUserDetailsManager)
    {
        passwordEncoder = pPasswordEncoder;
        userDetailsManager = pUserDetailsManager;
    }
    @GetMapping(path = "/employes")
    public String getCreateUserForm(@ModelAttribute(name="user") UserForm user, Model model)
    {
        if (user == null || !model.containsAttribute("user"))
        {
            model.addAttribute("user", new UserForm());
        }
        return "employees";
    }
    @PostMapping("/createManager")
    @Transactional
    public String createManager(
            @Valid @ModelAttribute(name = "user") UserForm user,
            BindingResult validation, Model model)
    {
        if (!user.getPassword().equals(user.getConfirmPassword()))
        {
            user.setConfirmPassword("");
            validation.addError(new FieldError("user", "confirmPassword",
                    "Les mots de passe ne correspondent pas"));
        }
        if (userDetailsManager.userExists(user.getLogin()))
        {
            user.setLogin("");
            validation.addError(new ObjectError("user", "Cet utilisateur existe déjà"));
        }
        if (validation.hasErrors())
        {
            return "/createManager";
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        // Roles for new manager
        Collection<? extends GrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"));
        UserDetails userDetails = new User(user.getLogin(), encodedPassword, roles);
        // Create the account in database with all its roles
        userDetailsManager.createUser(userDetails);

        // Create a UserProfile from UserForm and save it to the database
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(user.getLogin());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setAddress(user.getAddress());
        userProfile.setPhone(user.getPhone());
        userProfilService.addUserProfil(userProfile);

        return "redirect:/";
    }
    @GetMapping(path = "/souscription")
    public String getCreateCustomerForm(@ModelAttribute(name="user") UserForm user, Model model)
    {
        if (user == null || !model.containsAttribute("user"))
        {
            model.addAttribute("user", new UserForm());
        }
        return "subscribe";
    }
    @GetMapping("/profil")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        model.addAttribute("userProfile", userProfile);
        return "profile";
    }

    @PostMapping("/createUser")
    @Transactional
    public String createUser(
            @Valid @ModelAttribute(name = "user") UserForm user,
            BindingResult validation, Model model)
    {
        if (!user.getPassword().equals(user.getConfirmPassword()))
        {
            user.setConfirmPassword("");
            validation.addError(new FieldError("user", "confirmPassword",
                    "Les mots de passe ne correspondent pas"));
        }
        if (userDetailsManager.userExists(user.getLogin()))
        {
            user.setLogin("");
            validation.addError(new ObjectError("user", "Cet utilisateur existe déjà"));
        }
        if (user.getFormula() == null || user.getFormula().isEmpty())
        {
            validation.addError(new ObjectError("user", "Merci de choisir une formule."));
        }
        if (validation.hasErrors())
        {
            return "/createUser";
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        // Roles for new user
        Collection<? extends GrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new User(user.getLogin(), encodedPassword, roles);
        // Create the account in database with all its roles
        userDetailsManager.createUser(userDetails);

        // Create a UserProfile from UserForm and save it to the database
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(user.getLogin());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setAddress(user.getAddress());
        userProfile.setPhone(user.getPhone());
        userProfile.setFormula(user.getFormula());
        userProfilService.addUserProfil(userProfile);

        return "redirect:/";
    }

}
