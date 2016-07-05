package models;

import java.util.*;
import play.data.validation.Constraints.*;
import play.data.validation.*;

public class Configurator {
    
    @Required protected String name;
    @Required protected String prettiedName;
    @Required protected String location;
}