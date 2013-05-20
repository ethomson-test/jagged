package org.libgit2.jagged;

import java.io.BufferedWriter;
import java.io.FileWriter;

import junit.framework.Assert;

import org.junit.Test;

public class ReferenceTest
    extends GitTest
{
    @Test
    public void testCanGetHead()
    {
        Repository repository = new Repository(setupRepository("testrepo").getAbsolutePath());
        Branch headBranch = repository.getHead();

        Assert.assertTrue(headBranch.isCurrentRepositoryHead());
    }

    @Test
    public void testCanGetDetachedHead()
        throws Exception
    {
        final String repositoryPath = setupRepository("testrepo").getAbsolutePath();

        Repository repository = new Repository(repositoryPath);

        BufferedWriter w = new BufferedWriter(new FileWriter(repositoryPath + "/.git/HEAD"));
        w.write("055fe18dd1aef07991ebd08b4d54fc761dd022fb\n");
        w.close();

        Branch headBranch = repository.getHead();

        Assert.assertTrue(headBranch instanceof DetachedHead);
        Assert.assertTrue(headBranch.isCurrentRepositoryHead());
    }
}
