package file;

import dao.file.FileAccess;
import dao.file.HomeFileDao;
import model.CityList;
import model.Home;
import model.HomeType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomeDaoTest {

    private FileAccess getFileAccessStub() throws IOException {
        FileAccess stubFileAccess = mock(FileAccess.class);
        when(stubFileAccess.readAllLines())
                .thenReturn(new ArrayList<>(Arrays.asList(
                                "2 | MIAMI | APARTMENT",
                                "3 | NEW_YORK | ROOM",
                                "4 | MIAMI | HOUSE"
                        ))
                );

        return stubFileAccess;
    }

    @Test
    public void serialize_Success() {
        //-------------------------------------
        HomeFileDao homeDao = new HomeFileDao();
        String expected = "2 | MIAMI | APARTMENT";
        Home home = homeDao.read(expected);

        //-------------------------------------
        String actual = homeDao.serialize(home);

        //-------------------------------------
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void read_Success() {
        //-------------------------------------
        String line = "2 | MIAMI | APARTMENT ";
        HomeFileDao homeDao = new HomeFileDao();

        //-------------------------------------
        Home home = homeDao.read(line);

        //-------------------------------------
        Assert.assertEquals(2, home.getHostCode());
        Assert.assertEquals(CityList.MIAMI, home.getCity());
        Assert.assertEquals(HomeType.APARTMENT, home.getHomeType());
    }

    @Test
    public void readAll_Success() throws IOException {
        //-------------------------------------
        HomeFileDao homeFileDao = new HomeFileDao(getFileAccessStub());

        //-------------------------------------
        List<Home> homes = homeFileDao.readAll();

        //-------------------------------------
        Assert.assertEquals(3, homes.size());
        Assert.assertEquals(HomeType.APARTMENT, homes.get(0).getHomeType());
    }


    @Test
    public void create_Success() throws IOException {
        //-------------------------------------
        FileAccess stubFileAccess = getFileAccessStub();
        HomeFileDao homeDao = new HomeFileDao(stubFileAccess);
        Home home = homeDao.read("2 | NEW_YORK | ROOM");

        //-------------------------------------
        homeDao.create(home);

        //-------------------------------------
        verify(stubFileAccess).save(
                        "2 | MIAMI | APARTMENT\n" +
                        "3 | NEW_YORK | ROOM\n" +
                        "4 | MIAMI | HOUSE\n" +
                        "2 | NEW_YORK | ROOM");

    }

}
